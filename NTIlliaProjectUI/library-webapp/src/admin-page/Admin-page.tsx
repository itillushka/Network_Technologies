import React from 'react';
import { AppBar, Toolbar, Typography, Button, Container, Box } from '@mui/material';
import { Link } from 'react-router-dom';
import UserRegistrationForm from './UserRegistrationForm';
import AddBookForm from './AddBookForm';
import AddBookDetailsForm from './AddBookDetailsForm';
import './Admin-page.css';

const AdminPage = () => {
  return (
    <>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Admin Console
          </Typography>
          <Button color="inherit" component={Link} to="/admin">Admin Console</Button>
          <Button color="inherit" component={Link} to="/adminloan">Loan Management</Button>
        </Toolbar>
      </AppBar>
      <Container>
        <Box mt={4} className="container">
          <Typography variant="h4" component="h1" gutterBottom>
            Admin Page
          </Typography>
        </Box>
        <Box mt={4} className="container">
          <Typography variant="h5" component="h2" gutterBottom>
            Register New User
          </Typography>
          <UserRegistrationForm />
        </Box>
        <Box mt={4} className="container">
          <Typography variant="h5" component="h2" gutterBottom>
            Add New Book
          </Typography>
          <AddBookForm />
        </Box>
        <Box mt={4} className="container">
          <Typography variant="h5" component="h2" gutterBottom>
            Add Book Details
          </Typography>
          <AddBookDetailsForm />
        </Box>
      </Container>
    </>
  );
};

export default AdminPage;