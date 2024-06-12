import React from 'react';
import { AppBar, Toolbar, Typography, Box, TextField, Button } from '@mui/material';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
const AppBarComponent = () => {
	const {t, i18n} = useTranslation();

	const changeLanguage = (lng: string) => {
		i18n.changeLanguage(lng);
	};
  return (
		<AppBar position="static" sx={{ flexGrow: 1 }}>
			<Toolbar>
				<Button color="inherit" onClick={() => changeLanguage('en')}>
					EN
				</Button>
				<Button color="inherit" onClick={() => changeLanguage('ua')}>
					UA
				</Button>
				<Typography variant="h6" className="title">
					{t('library')}
				</Typography>
				<Button color="inherit">
					<Link to="/login" style={{ color: '#FDF5E6', textDecoration: 'none' }}>{t('Login')}</Link>
				</Button>
				<Button color="inherit">
					<Link to="/book" style={{ color: '#FDF5E6', textDecoration: 'none' }}>{t('Books')}</Link>
				</Button>
				<Button color="inherit">
					<Link to="/loan" style={{ color: '#FDF5E6', textDecoration: 'none' }}>{t('Loans')}</Link>
				</Button>
				<Button color="inherit">
					<Link to="/admin" style={{ color: '#FDF5E6', textDecoration: 'none' }}>{t('Admin')}</Link>
				</Button>
				<Button color="inherit">
					<Link to="/adminLoan" style={{ color: '#FDF5E6', textDecoration: 'none' }}>{t('Admin Loans')}</Link>
				</Button>
			</Toolbar>
		</AppBar>
  );
};

export default AppBarComponent;