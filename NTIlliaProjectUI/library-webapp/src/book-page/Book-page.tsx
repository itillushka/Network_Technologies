import React, { useState, useEffect } from 'react';
import './Book-page.css';
import { AppBar, Toolbar, Box, Card, CardMedia, CardContent, Typography, Collapse, TextField, Container, IconButton, Button } from '@mui/material';
import { ArrowBack, ArrowForward } from '@mui/icons-material';
import { LibraryClient } from '../api/library-client';
import { BookResponseDto } from '../api/dto/book-response.dto';
import kobzar from './kobzar.jpg';

const libraryClient = new LibraryClient();

const BookPage = () => {
  // Dummy data for books
  const [books, setBooks] = useState<BookResponseDto[]>([]);
  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await libraryClient.getAllBooks();

        if (response.success) {
            console.log('Unfiltered books:', response.data); // Output unfiltered books to console

            const mappedBooks = response.data
            .map((book: BookResponseDto) => ({
              id: book.bookID,
              year: book.yearPublished ? book.yearPublished : "No year",
              isbn: book.ISBN ? book.ISBN : "No ISBN",
              title: book.title ? book.title : "No title",
              author: book.author ? book.author : "No author",
              publisher: book.publisher ? book.publisher : "No publisher",
              isAvailable: book.isAvailable ? book.isAvailable : "No availability",
            }))
          setBooks(mappedBooks);
        } else {
          console.error(`Failed to fetch books: ${response.status}`);
        }
      } catch (error) {
        console.error(`Failed to fetch books: ${error}`);
      }
    };

    fetchBooks().then(r => r);
  }, []);

  const [expandedId, setExpandedId] = useState<number | null>(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentIndex, setCurrentIndex] = useState(0);
  const [transitioning, setTransitioning] = useState(false);

  const handleExpandClick = (id: number | undefined) => {
    setExpandedId(expandedId === id ? null : id || null);
  };

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  const nextBook = () => {
    if (transitioning) return;
    setTransitioning(true);
    setTimeout(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % books.length);
      setTransitioning(false);
    }, 500);
  };

  const prevBook = () => {
    if (transitioning) return;
    setTransitioning(true);
    setTimeout(() => {
      setCurrentIndex((prevIndex) => (prevIndex - 1 + books.length) % books.length);
      setTransitioning(false);
    }, 500);
  };

  /*const filteredBooks = books.filter((book) =>
    book.title ? book.title.toLowerCase().includes(searchTerm.toLowerCase()) : false
  );*/

  const displayBooks = [
    books[(currentIndex - 1 + books.length) % books.length],
    books[currentIndex],
    books[(currentIndex + 1) % books.length],
  ].filter(Boolean);

  return (
    <>
      <AppBar position="static" className="AppBar">
        <Toolbar sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <Typography variant="h6" component="div">
            My Book Store
          </Typography>
          <Box sx={{ backgroundColor: '#DEB887', borderRadius: '5px' }}>
            <TextField
              label="Search"
              variant="outlined"
              value={searchTerm}
              onChange={handleSearchChange}
              sx={{ marginLeft: 'auto' }}
            />
          </Box>
          <Box sx={{ width: '10%' }} />
        </Toolbar>
      </AppBar>
      <Container className="container">
        <Box mt={2} className="wheel-container">
          <IconButton onClick={prevBook}>
            <ArrowBack />
          </IconButton>
          <Box className={`wheel ${transitioning ? 'transitioning' : ''}`}>
            {displayBooks.map((book, index) => (
              <Card className={`Card ${index === 1 ? 'center' : 'side'}`} key={index}>
                <CardMedia
                  component="img"
                  className="book-cover"
                  image={kobzar}
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
                      <Typography paragraph>ISBN: {book.ISBN}</Typography>
                      <Typography paragraph>Publisher: {book.publisher}</Typography>
                    </CardContent>
                  </Collapse>
                </CardContent>
              </Card>
            ))}
          </Box>
          <IconButton onClick={nextBook}>
            <ArrowForward />
          </IconButton>
        </Box>
      </Container>
      <Box className="footer">
        <Typography variant="body1">My Book Store © 2024</Typography>
      </Box>
    </>
  );
};

export default BookPage;
