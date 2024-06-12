import React from 'react';
import { Link } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Container } from '@mui/material';
import './Home-page.css'; // Import the CSS file
import { useTranslation } from 'react-i18next';
import AppBarComponent from '../components/AppBarComponent';

const HomePage = () => {
  const {t, i18n} = useTranslation();
  return (
    <div>
      <AppBarComponent />
      <Container className="container">
        <div className="home-cover">
          <h1>	{t('Welcome')}</h1>
          <p>{t('Description')}</p>
        </div>
      </Container>
    </div>
  );
};

export default HomePage;