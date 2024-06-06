import React, { useState, useEffect } from 'react';
import './Loan-page.css';
import { Box, List, ListItem, CardMedia, ListItemText, Card, CardContent } from '@mui/material';
import { Container } from '@mui/material';
import { LibraryClient } from '../api/library-client';
import { LoanResponseDto } from '../api/dto/loan-response.dto';
import AppBarComponent from '../components/AppBarComponent';
import { BookResponseDto } from '../api/dto/book-response.dto';

const LoanPage = () => {
  const [loans, setLoans] = useState<LoanResponseDto[]>([]);
  const [books, setBooks] = useState<BookResponseDto[]>([]);
  const libraryClient = new LibraryClient();

  useEffect(() => {
    const fetchData = async () => {
      const [loansResponse, booksResponse] = await Promise.all([
        libraryClient.getAllLoans(),
        libraryClient.getAllBooks(),
      ]);

      if (loansResponse.success && booksResponse.success) {
        const bookDetailsPromises = loansResponse.data.map((loan: LoanResponseDto) =>
          libraryClient.getBookDetail(typeof loan.bookID === 'number' ? loan.bookID :-1)
        );
        const bookDetailsResponses = await Promise.all(bookDetailsPromises);

        const mappedLoans = loansResponse.data.map((loan: LoanResponseDto, index: number) => {
          const book = booksResponse.data.find((book: BookResponseDto) => book.bookID === loan.bookID);
          const bookDetail = bookDetailsResponses[index].data;
          return {
            ...loan,
            coverImageURL: bookDetail?.coverImageURL,
            title: book?.title,
            author: book?.author,
          };
        });

        setLoans(mappedLoans);
        setBooks(booksResponse.data);
      }
    };

    fetchData();
  }, []);

  return (
    <>
      <AppBarComponent />
      <Container className="container">
        <Box mt={2}>
          <List>
            {loans.map((loan) => (
              <ListItem key={loan.loanID}>
                <Card className="LoanCard">
                  <CardMedia
                    component="img"
                    className="loan-cover"
                    image={loan.coverImageURL}
                    alt={books.find(book => book.bookID === loan.bookID)?.title}
                    sx={{ height: 300, width: 200 }}
                  />
                  <CardContent>
                    <Box>
                      <ListItemText
                        primary={`Title: ${books.find(book => book.bookID === loan.bookID)?.title}`}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        primary={`Author: ${books.find(book => book.bookID === loan.bookID)?.author}`}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        primary={`Loan ID: ${loan.loanID}`}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={`Loan Date: ${loan.loanDate}`}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={`Due Date: ${loan.dueDate}`}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={`Return Date: ${loan.returnDate}`}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={`Status: ${loan.status}`}
                      />
                    </Box>
                  </CardContent>
                </Card>
              </ListItem>
            ))}
          </List>
        </Box>
      </Container>
    </>
  );
};

export default LoanPage;