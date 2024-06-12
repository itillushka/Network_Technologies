import React, { useState, useEffect } from 'react';
import './Loan-page.css';
import {
  Box,
  List,
  ListItem,
  CardMedia,
  ListItemText,
  Card,
  CardContent,
  Button,
  CardActions,
  Modal,
  TextField,
  Rating
} from '@mui/material';
import { Container } from '@mui/material';
import { LibraryClient } from '../api/library-client';
import { LoanResponseDto } from '../api/dto/loan-response.dto';
import AppBarComponent from '../components/AppBarComponent';
import { BookResponseDto } from '../api/dto/book-response.dto';
import { ReturnLoanRequestDto } from '../api/dto/return-loan-request.dto';
import { ReviewCreateRequestDto } from '../api/dto/review-create-request.dto';
import { useTranslation } from 'react-i18next';

const LoanPage = () => {
  const {t, i18n} = useTranslation();
  const [loans, setLoans] = useState<LoanResponseDto[]>([]);
  const [books, setBooks] = useState<BookResponseDto[]>([]);

  const [open, setOpen] = useState(false);
  const [comment, setComment] = useState('');
  const [rating, setRating] = useState(0);
  const [selectedBookId, setSelectedBookId] = useState<number | null>(null);

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

  const handleAddReviewClick = (bookId: number) => {
    setSelectedBookId(bookId);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    if (selectedBookId !== null) {
      const reviewRequest = new ReviewCreateRequestDto();
      reviewRequest.comment = comment;
      reviewRequest.rating = rating;
      reviewRequest.reviewDate = new Date().toISOString().split('T')[0];

      const response = await libraryClient.createReview(reviewRequest, selectedBookId);
      if (response === 201) {
        alert('Review added successfully.');
      } else {
        alert(`Failed to add review.${response}`);
        console.log(`Failed to add review.${reviewRequest.comment, reviewRequest.rating, reviewRequest.reviewDate, selectedBookId}`)
      }
    }
    handleClose();
  };

  const handleReturnClick = async (bookId: number) => {
  const returnLoanRequest = new ReturnLoanRequestDto();
  returnLoanRequest.bookId = bookId;
  returnLoanRequest.returnDate = new Date().toISOString().split('T')[0]; // get today's date in YYYY-MM-DD format

  const response = await libraryClient.returnLoan(returnLoanRequest);
  if (response === 200) {
    alert(`Book with ID: ${bookId} returned successfully.`);
  } else if (response === 409) {
    alert(`Failed to return book because it is not yet approved`);
  } else {
    alert(`Failed to return book with ID: ${bookId}.`);
  }
};

  return (
    <>
      <AppBarComponent />
      <Modal
        open={open}
        onClose={handleClose}
      >
        <form onSubmit={handleFormSubmit} className = "modalContent">
          <h2 className="modalTitle">Leave your review!</h2>
          <TextField
            label="Comment"
            value={comment}
            onChange={(event) => setComment(event.target.value)}
            className="formField"
          />
          <Rating
            name="simple-controlled"
            value={rating}
            onChange={(event, newValue) => {
              setRating(newValue !== null ? newValue : 0);
            }}
            precision={0.5}
            size="large"
            className="formField"
          />
          <Button type="submit" className="submitButton">Submit</Button>
        </form>
      </Modal>
      <Container className="container">
        <Box mt={2} style={{marginTop: '50px'}}>
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
                    <Box>
                      <ListItemText
                        secondary={t('Status') + (loan.status)}
                      />
                    </Box>
                  </CardContent>
                  <Box style={{ marginLeft: '440px' , marginTop: '100px' }}>
                    <CardActions>
                    <Box display="flex" flexDirection="column" justifyContent="flex-end">
                      <Box mb={1}>
                      <Button fullWidth onClick={() => handleAddReviewClick(typeof loan.bookID === 'number' ? loan.bookID : -1)} type="submit"
                              color="primary"
                              variant="contained"
                              className="centered-button">{t('Add Review')}</Button>
                        </Box>
                      <Box display="flex" justifyContent="flex-end">
                      <Button fullWidth onClick={() => handleReturnClick(typeof loan.bookID === 'number' ? loan.bookID : -1)} type="submit"
                              color="primary"
                              variant="contained"
                              className="centered-button">{t('Return')}</Button>
                    </Box>
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

export default LoanPage;