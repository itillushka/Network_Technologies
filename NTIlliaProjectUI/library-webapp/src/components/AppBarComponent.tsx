import React from 'react';
import { AppBar, Toolbar, Typography, Box, TextField, Button } from '@mui/material';
import { Link } from 'react-router-dom';
import { useTranslation } from 'react-i18next';
const AppBarComponent = () => {
	const {t, i18n} = useTranslation();
  return (
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
  );
};

export default AppBarComponent;