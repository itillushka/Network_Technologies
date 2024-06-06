import React, { useState, useEffect } from 'react';
import './Book-page.css';
import { Box, Card, CardMedia, CardContent, Typography, Collapse, Container, Button, Grid } from '@mui/material';
import { LibraryClient } from '../api/library-client';
import { BookResponseDto } from '../api/dto/book-response.dto';
import AppBarComponent from '../components/AppBarComponent';
import { BookDetailsResponseDto } from '../api/dto/book-details-response.dto';
import { CreateLoanRequestDto } from '../api/dto/create-loan-request.dto';

const libraryClient = new LibraryClient();

const BookPage = () => {

  const handleBorrowClick = async (bookID: number) => {
    const today = new Date();
    const dueDate = new Date();
    dueDate.setMonth(today.getMonth() + 1);

    const loanRequest: CreateLoanRequestDto = {
      bookID: bookID,
      loanDate: today.toISOString().split('T')[0],
      dueDate: dueDate.toISOString().split('T')[0],
    };

    try {
      const response = await libraryClient.createLoan(loanRequest);
      if (response === 201) {
        alert('Loan created successfully ' + response);
      } else {
        alert('Failed to create loan ' + response);
      }
    } catch (error) {
      alert('Failed to create loan: ' + error);
    }
  };

  const [books, setBooks] = useState<BookResponseDto[]>([]);
  const [bookDetails, setBookDetails] = useState<BookDetailsResponseDto[]>([]);
  const [expandedId, setExpandedId] = useState<number | null>(null);
  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await libraryClient.getAllBooks();

        if (response.success) {
          console.log('Unfiltered books:', response.data); // Output unfiltered books to console

          const mappedBooks = response.data
            .map((book: BookResponseDto) => ({
              bookID: book.bookID,
              yearPublished: book.yearPublished ? book.yearPublished : "No year",
              isbn: book.isbn ? book.isbn : "No ISBN",
              title: book.title ? book.title : "No title",
              author: book.author ? book.author : "No author",
              publisher: book.publisher ? book.publisher : "No publisher",
              isAvailable: book.isAvailable ? book.isAvailable : "No availability",
            }));

          setBooks(mappedBooks);
          console.log('Mapped books:', mappedBooks); // Output mapped books to console
          // Fetch book details after setting books
          const details = await Promise.all(mappedBooks.map(async (book: BookResponseDto) => {
            if (book.bookID != null) {
              try {
                const detailResponse = await libraryClient.getBookDetail(book.bookID);
                console.log('Unfiltered detail:', detailResponse.data);
                return detailResponse.data;
              } catch (error) {
                console.error(`Failed to fetch details for book ${book.bookID}:`, error);
              }
            }
          }));

          setBookDetails(details);
        } else {
          console.error(`Failed to fetch books: ${response.status}`);
        }
      } catch (error) {
        console.error(`Failed to fetch books: ${error}`);
      }
    };

    fetchBooks().then(r => r);
  }, []);
  const handleExpandClick = (id: number | undefined) => {
    setExpandedId(expandedId === id ? null : id || null);
  };
  return (
    <>
      <AppBarComponent />
      <Container className="container">
        <Box>
          <Grid container justifyContent="center" spacing={2} className="book-list">
            {books.map((book, index) => (
              <Grid item xs={12} sm={4} md={4} lg={4} key={index}>
                <Card className="Card">
                  <CardMedia
                    component="img"
                    className="book-cover"
                    image={bookDetails.find(detail => detail.bookid === book.bookID)?.coverImageURL}
                    alt={book.title}
                  />
                  <CardContent>
                    <Typography variant="h5" component="div">
                      {book.title}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      {book.author}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      {book.yearPublished}
                    </Typography>
                    <Button onClick={() => handleExpandClick(book.bookID)}>
                      {expandedId === book.bookID ? 'Less' : 'More'}
                    </Button>
                    <Collapse in={expandedId === book.bookID} timeout="auto" unmountOnExit>
                      <CardContent>
                        <Typography paragraph>ISBN: {book.isbn}</Typography>
                        <Typography paragraph>Publisher: {book.publisher}</Typography>
                        <Typography
                          paragraph>Genre: {bookDetails.find(detail => detail.bookid === book.bookID)?.genre}</Typography>
                        <Typography
                          paragraph>Summary: {bookDetails.find(detail => detail.bookid === book.bookID)?.summary}</Typography>
                        <Button
                          type="submit"
                          color="primary"
                          variant="contained"
                          className="centered-button"
                          onClick={() => handleBorrowClick(typeof book.bookID === 'number' ? book.bookID :-1)}> Borrow
                        </Button>
                      </CardContent>
                    </Collapse>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </Box>
      </Container>
      <Box className="footer">
        <Typography variant="body1">My Book Store © 2024</Typography>
      </Box>
    </>
  );
};

export default BookPage;