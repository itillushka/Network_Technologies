import React, { useState, useEffect } from 'react';
import loanImage1 from './kobzar.jpg';
import './Loan-page.css';
import { AppBar, Toolbar, Box, List, ListItem, CardMedia, ListItemText, Typography, Card, CardContent } from '@mui/material';
import { Container } from '@mui/material';
import { LibraryClient } from '../api/library-client';
import { LoanResponseDto } from '../api/dto/loan-response.dto';

const LoanPage = () => {
  const [loans, setLoans] = useState<LoanResponseDto[]>([]);
  const libraryClient = new LibraryClient();

  useEffect(() => {
    const fetchLoans = async () => {
      const response = await libraryClient.getAllLoans();
      if (response.success) {
        const loansWithCoverImage = response.data.map((loan: LoanResponseDto)=> ({ ...loan, coverImage: loanImage1 }));
        setLoans(loansWithCoverImage);
      }
    };

    fetchLoans();
  }, []);

  return (
    <>
      <AppBar position="static" className="AppBar">
        <Toolbar>
          <Typography variant="h6" component="div">
            My Loan Store
          </Typography>
        </Toolbar>
      </AppBar>
      <Container className="container">
        <Box mt={2}>
          <List>
            {loans.map((loan) => (
              <ListItem key={loan.loanID}>
                <Card className="LoanCard">
                  <CardMedia
                    component="img"
                    className="loan-cover"
                    image={loanImage1}
                    alt={"Book title"}
                    sx={{ height: 300, width: 200 }}
                  />
                  <CardContent>
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