import React from 'react';
import { Container, Box, Typography } from '@mui/material';
import UserRegistrationForm from './UserRegistrationForm';
import AddBookForm from './AddBookForm';
import AddBookDetailsForm from './AddBookDetailsForm';
import './Admin-page.css';

const AdminPage = () => {
  return (
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
  );
};

export default AdminPage;