import React from 'react';
import kobzar from './kobzar.jpg';
import witcher from './witcher.jpg';
import witcher1 from './witcher1.jpg';
import './Book-page.css';
import {AppBar, Toolbar,Box, Grid, Card, CardMedia, CardContent, Typography } from '@mui/material';
import { Container } from '@mui/material';

const BookPage = () => {
	// Dummy data for books
	const books = [
		{
			id: 1,
			title: 'Book 1',
			author: 'Author 1',
			coverImage: kobzar,
		},
		{
			id: 2,
			title: 'Book 2',
			author: 'Author 2',
			coverImage: witcher,
		},
		{
			id: 3,
			title: 'Book 3',
			author: 'Author 3',
			coverImage: witcher1,
		},
		// Add more books as needed
	];


return (
  <>
    <AppBar position="static" className="AppBar">
      <Toolbar>
        <Typography variant="h6" component="div">
          My Book Store
        </Typography>
      </Toolbar>
    </AppBar>
    <Container className="container">
      <Box mt={2}>
        <Grid container spacing={2}>
          {books.map((book) => (
            <Grid item xs={12} sm={6} md={4} key={book.id}>
              <Card className="Card">
                <CardMedia
                  component="img"
                  className="book-cover"
                  image={book.coverImage}
                  alt={book.title}
                />
                <CardContent>
                  <Typography variant="h5" component="div">
                    {book.title}
                  </Typography>
                  <Typography variant="body2" color="text.secondary">
                    {book.author}
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

export default BookPage;
