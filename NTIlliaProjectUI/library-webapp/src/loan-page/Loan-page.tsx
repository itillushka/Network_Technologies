import React from 'react';
import loanImage1 from './kobzar.jpg';
import loanImage2 from './witcher.jpg';
import loanImage3 from './witcher1.jpg';
import './Loan-page.css';
import { AppBar, Toolbar, Box, Grid, Card, CardMedia, CardContent, Typography } from '@mui/material';
import { Container } from '@mui/material';

const LoanPage = () => {
  // Dummy data for loans
  const loans = [
    {
      id: 1,
      title: 'Loan 1',
      lender: 'Lender 1',
      coverImage: loanImage1,
    },
    {
      id: 2,
      title: 'Loan 2',
      lender: 'Lender 2',
      coverImage: loanImage2,
    },
    {
      id: 3,
      title: 'Loan 3',
      lender: 'Lender 3',
      coverImage: loanImage3,
    },
    // Add more loans as needed
  ];

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
          <Grid container spacing={2}>
            {loans.map((loan) => (
              <Grid item xs={12} sm={6} md={4} key={loan.id}>
                <Card className="Card">
                  <CardMedia
                    component="img"
                    className="loan-cover"
                    image={loan.coverImage}
                    alt={loan.title}
                  />
                  <CardContent>
                    <Typography variant="h5" component="div">
                      {loan.title}
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      {loan.lender}
                    </Typography>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </Box>
      </Container>
    </>
  );
};

export default LoanPage;