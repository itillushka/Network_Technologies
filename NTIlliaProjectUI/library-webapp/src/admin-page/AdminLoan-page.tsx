import React, { useEffect, useState } from 'react';
import loanImage1 from './kobzar.jpg';
import './AdminLoan-page.css';
import { AppBar, Toolbar, Box, List, ListItem, CardMedia, ListItemText, Typography, Card, CardContent, Select, MenuItem, Button } from '@mui/material';
import { Container } from '@mui/material';
import { SelectChangeEvent } from '@mui/material';
import { LoanResponseDto } from '../api/dto/loan-response.dto';
import { LibraryClient } from '../api/library-client';
import { Link } from 'react-router-dom';

const AdminLoanPage = () => {
  const [loans, setLoans] = useState<LoanResponseDto[]>([]);
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
    const fetchLoans = async () => {
      const response = await libraryClient.getAllLoansAdmin();
      if (response.success) {
        const loansWithCoverImage = response.data.map((loan: LoanResponseDto)=> ({ ...loan, coverImage: loanImage1 }));
        setLoans(loansWithCoverImage);
      }
    };

    fetchLoans();
  }, []);

  const handleStatusChange = (event: SelectChangeEvent<string>, loanId: number) => {
  const newStatus = event.target.value;
  setLoans(loans.map(loan => loan.loanID === loanId ? { ...loan, status: newStatus } : loan));
};

  return (
    <>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Admin Loan Management
          </Typography>
          <Button color="inherit" component={Link} to="/admin">Admin Console</Button>
          <Button color="inherit" component={Link} to="/adminloan">Loan Management</Button>
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
                        primary={loan.loanID}
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
                    <Box>
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
                      <Button onClick={() => handleConfirmClick(typeof loan.loanID === 'number' ? loan.loanID :0)}>Confirm</Button>
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

export default AdminLoanPage;