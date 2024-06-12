import React, { useEffect, useState } from 'react';
import './AdminLoan-page.css';
import {
  AppBar,
  Toolbar,
  Box,
  List,
  ListItem,
  CardMedia,
  ListItemText,
  Typography,
  Card,
  CardContent,
  Select,
  MenuItem,
  Button,
  CardActions
} from '@mui/material';
import { Container } from '@mui/material';
import { SelectChangeEvent } from '@mui/material';
import { LoanResponseDto } from '../api/dto/loan-response.dto';
import { LibraryClient } from '../api/library-client';
import { Link } from 'react-router-dom';
import AppBarComponent from '../components/AppBarComponent';
import { BookResponseDto } from '../api/dto/book-response.dto';
import { useTranslation } from 'react-i18next';

const AdminLoanPage = () => {
  const {t, i18n} = useTranslation();
  const [loans, setLoans] = useState<LoanResponseDto[]>([]);
  const [books, setBooks] = useState<BookResponseDto[]>([]);
  const libraryClient = new LibraryClient();

  const handleConfirmClick = async (loanId: number) => {
    const loanToUpdate = loans.find(loan => loan.loanID === loanId);
    if (!loanToUpdate) {
      console.error(`Loan with ID ${loanId} not found`);
      return;
    }

    const updateLoanRequest = {
      status: loanToUpdate.status,
    };

    try {
      const response = await libraryClient.updateLoan(updateLoanRequest, loanId)
      if (response === 200) {
        console.log(`Loan with ID ${loanId} updated successfully`);
      } else {
        console.error(`Failed to update loan with ID ${loanId}`);
      }
    } catch (error) {
      console.error(`Failed to update loan with ID ${loanId}:`, error);
    }
  };


  useEffect(() => {
    const fetchData = async () => {
      const [loansResponse, booksResponse] = await Promise.all([
        libraryClient.getAllLoansAdmin(),
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

  const handleStatusChange = (event: SelectChangeEvent<string>, loanId: number) => {
  const newStatus = event.target.value;
  setLoans(loans.map(loan => loan.loanID === loanId ? { ...loan, status: newStatus } : loan));
};

  return (
    <>
      <AppBarComponent/>
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
                        primary={t('Title') + (books.find(book => book.bookID === loan.bookID)?.title)}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        primary={t('Author') + (books.find(book => book.bookID === loan.bookID)?.author)}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        primary={t('Loan ID') + (loan.loanID)}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={t('Loan Date') + (loan.loanDate)}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={t('Due Date') + (loan.dueDate)}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={t('Return Date') + (loan.returnDate)}
                      />
                    </Box>
                  </CardContent>
                    <Box style={{ marginLeft: '440px' , marginTop: '100px' }}>
                      <CardActions>
                        <Box display="flex" flexDirection="column" justifyContent="flex-end">
                          <Box mb={1}>
                      <Select
                        value={loan.status}
                        onChange={(event) => handleStatusChange(event, typeof loan.loanID === 'number' ? loan.loanID :0)}
                      >
                        <MenuItem value="PENDING">PENDING</MenuItem>
                        <MenuItem value="REJECTED">REJECTED</MenuItem>
                        <MenuItem value="APPROVED">APPROVED</MenuItem>
                        <MenuItem value="RETURNED">RETURNED</MenuItem>
                        <MenuItem value="OVERDUE">OVERDUE</MenuItem>
                        <MenuItem value="PENDING_RETURN">PENDING_RETURN</MenuItem>
                      </Select>
                            </Box>

                      <Button className={"submitButton"} onClick={() => handleConfirmClick(typeof loan.loanID === 'number' ? loan.loanID :0)}>{t('CONFIRM')}</Button>

                    </Box>
                  </CardActions>
                    </Box>
                </Card>
              </ListItem>
            ))}
          </List>
        </Box>
      </Container>
    </>
  );
};

export default AdminLoanPage;