import React from 'react';
import loanImage1 from './kobzar.jpg';
import loanImage2 from './witcher.jpg';
import loanImage3 from './witcher1.jpg';
import './Loan-page.css';
import { AppBar, Toolbar, Box, List, ListItem, CardMedia, ListItemText, Typography, Card, CardContent } from '@mui/material';
import { Container } from '@mui/material';

const LoanPage = () => {
  // Dummy data for loans
  const loans = [
    {
      id: 1,
      title: 'Loan 1',
      coverImage: loanImage1,
      loanDate: '2023-01-01',
      returnDate: '2023-01-31',
      dueDate: '2023-01-31',
    },
    {
      id: 2,
      title: 'Loan 2',
      coverImage: loanImage2,
      loanDate: '2023-02-01',
      returnDate: '2023-02-28',
      dueDate: '2023-02-28',
    },
    {
      id: 3,
      title: 'Loan 3',
      coverImage: loanImage3,
      loanDate: '2023-03-01',
      returnDate: '2023-03-31',
      dueDate: '2023-03-31',
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
          <List>
            {loans.map((loan) => (
              <ListItem key={loan.id}>
                <Card className="LoanCard">
                  <CardMedia
                    component="img"
                    className="loan-cover"
                    image={loan.coverImage}
                    alt={loan.title}
                    sx={{ height: 300, width: 200 }}
                  />
                  <CardContent>
                    <Box>
                      <ListItemText
                        primary={loan.title}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={`Loan Date: ${loan.loanDate}`}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={`Return Date: ${loan.returnDate}`}
                      />
                    </Box>
                    <Box>
                      <ListItemText
                        secondary={`Due Date: ${loan.dueDate}`}
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