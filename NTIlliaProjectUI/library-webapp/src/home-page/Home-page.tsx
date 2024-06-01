import React from 'react';
import { Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';
import './Home-page.css'; // Import the CSS file
import { useTranslation } from 'react-i18next';

const HomePage = () => {
  const {t, i18n} = useTranslation();
  return (
    <div>
      <AppBar position="static" sx={{ flexGrow: 1 }}>
        <Toolbar>
          <Typography variant="h6" className="title">
            {t('library')}
          </Typography>
          <Button color="inherit">
            <Link to="/login" style={{ color: '#FDF5E6', textDecoration: 'none' }}>Login</Link>
          </Button>
          <Button color="inherit">
            <Link to="/book" style={{ color: '#FDF5E6', textDecoration: 'none' }}>Books</Link>
          </Button>
          <Button color="inherit">
            <Link to="/loan" style={{ color: '#FDF5E6', textDecoration: 'none' }}>Loans</Link>
          </Button>
          <Button color="inherit">
            <Link to="/admin" style={{ color: '#FDF5E6', textDecoration: 'none' }}>Admin</Link>
          </Button>
          <Button color="inherit">
            <Link to="/adminLoan" style={{ color: '#FDF5E6', textDecoration: 'none' }}>Admin Loans</Link>
          </Button>
        </Toolbar>
      </AppBar>
      <Container className="container">
        <div className="home-cover">
          <h1>Welcome to the Digital Vintage Library</h1>
          <p>This is a digital platform where you can find and loan vintage books.</p>
        </div>
      </Container>
    </div>
  );
};

export default HomePage;