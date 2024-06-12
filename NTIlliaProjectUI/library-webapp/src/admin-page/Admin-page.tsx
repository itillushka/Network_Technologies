import React from 'react';
import { AppBar, Toolbar, Typography, Button, Container, Box } from '@mui/material';
import { Link } from 'react-router-dom';
import UserRegistrationForm from './UserRegistrationForm';
import AddBookForm from './AddBookForm';
import AddBookDetailsForm from './AddBookDetailsForm';
import './Admin-page.css';
import { LibraryClient } from '../api/library-client';
import { RegisterUserRequestDto } from '../api/dto/register-user-request.dto';
import { CreateBookRequestDto } from '../api/dto/create-book-request.dto';
import { AddBookDetailsRequestDto } from '../api/dto/add-book-details-request.dto';
import { DeleteBookRequestDto } from '../api/dto/delete-book-details-request.dto';
import DeleteBookForm from './DeleteBookForm';
import AppBarComponent from '../components/AppBarComponent';
import { useTranslation } from 'react-i18next';

const AdminPage = () => {
  const libraryClient = new LibraryClient();
  const {t, i18n} = useTranslation();

  const handleUserRegistration = async (values: RegisterUserRequestDto) => {
    const status = await libraryClient.registerUser(values);
    if (status === 201) {
      alert('User created successfully');
    } else if (status === 403) {
      alert('You do not have permission to create a user');
    } else {
      alert(`User registration failed with status: ${status}`);
    }
  };

  const handleCreateBook = async (values: CreateBookRequestDto) => {
    const status = await libraryClient.createBook(values);
    if (status === 201) {
      alert('Book created successfully');
    } else if (status === 403) {
      alert('You do not have permission to create a book');
    } else {
      alert(`Book creation failed with status: ${status}`);
    }
  };

  const handleAddBookDetails = async (values: AddBookDetailsRequestDto) => {
    const status = await libraryClient.addBookDetails(values);
    if (status === 201) {
      alert('Book created successfully');
    } else if (status === 403) {
      alert('You do not have permission to create a book');
    } else {
      alert(`Book creation failed with status: ${status}`);
    }
  };

  const handleDeleteBook = async (values: DeleteBookRequestDto) => {
    const status = await libraryClient.deleteBook(values);
    if (status === 204) {
      alert('Book deleted successfully');
    } else if (status === 403) {
      alert('You do not have permission to delete a book');
    } else {
      alert(`Book deletion failed with status: ${status}`);
    }
  };

  return (
    <>
      <AppBarComponent/>
      <Container>
        <Box mt={5} className="container">
          <Typography variant="h4" component="h1" gutterBottom>
            {t('Admin Page')}
          </Typography>
        </Box>
        <Box mt={5} className="container">
          <Typography variant="h5" component="h2" gutterBottom>
            {t('Register New User')}
          </Typography>
          <UserRegistrationForm onSubmit={handleUserRegistration} />
        </Box>
        <Box mt={5} className="container">
          <Typography variant="h5" component="h2" gutterBottom>
            {t('Add New Book')}
          </Typography>
          <AddBookForm onSubmit={handleCreateBook}/>
        </Box>
        <Box mt={5} className="container">
          <Typography variant="h5" component="h2" gutterBottom>
            {t('Add Book Details')}
          </Typography>
          <AddBookDetailsForm onSubmit={handleAddBookDetails}/>
        </Box>
        <Box mt={5} className="container">
          <Typography variant="h5" component="h2" gutterBottom>
            {t('Delete Book')}
          </Typography>
          <DeleteBookForm onSubmit={handleDeleteBook}/>
        </Box>
      </Container>
    </>
  );
};

export default AdminPage;