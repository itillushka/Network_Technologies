import React, { useState } from 'react';
import kobzar from './kobzar.jpg';
import witcher from './witcher.jpg';
import witcher1 from './witcher1.jpg';
import './Book-page.css';
import { AppBar, Toolbar, Box, Grid, Card, CardMedia, CardContent, Typography, Collapse, Button, TextField } from '@mui/material';
import { Container } from '@mui/material';

const BookPage = () => {
  // Dummy data for books
  const books = [
    {
      id: 1,
      title: 'Book 1',
      author: 'Author 1',
      year: '2000',
      isbn: '1234567890',
      publisher: 'Publisher 1',
      genre: 'Genre 1',
      summary: 'Summary 1',
      coverImage: kobzar,
    },
    {
      id: 2,
      title: 'Book 2',
      author: 'Author 2',
      year: '2020',
      isbn: '1234567890',
      publisher: 'Publisher 2',
      genre: 'Genre 2',
      summary: 'Summary 2',
      coverImage: witcher,
    },
    {
      id: 3,
      title: 'Book 3',
      author: 'Author 3',
      year: '2021',
      isbn: '1234567890',
      publisher: 'Publisher 3',
      genre: 'Genre 3',
      summary: 'Summary 3',
      coverImage: witcher1,
    },
    {
      id: 4,
      title: 'Book 4',
      author: 'Author 4',
      year: '2221',
      isbn: '1234567890',
      publisher: 'Publisher 4',
      genre: 'Genre 4',
      summary: 'Summary 4',
      coverImage: witcher,
    }
    // Add more books as needed
  ];


  const [expandedId, setExpandedId] = useState<number | null>(null);
  const [searchTerm, setSearchTerm] = useState('');

  const handleExpandClick = (id: number) => {
    setExpandedId(expandedId === id ? null : id);
  };

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  const filteredBooks = books.filter((book) =>
    book.title.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <>
      <AppBar position="static" className="AppBar">
        <Toolbar sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <Typography variant="h6" component="div">
            My Book Store
          </Typography>
          <Box sx={{ backgroundColor: '#DEB887', borderRadius: '5px' }}> {/* This will give the TextField a background color */}
            <TextField
            label="Search"
            variant="outlined"
            value={searchTerm}
            onChange={handleSearchChange}
            sx={{ marginLeft: 'auto' }} // This will push the TextField to the right
          />
          </Box>
          <Box sx={{ width: '10%' }} />
        </Toolbar>
      </AppBar>
      <Container className="container">
        <Box mt={2}>
          <Grid container spacing={2}>
            {filteredBooks.map((book) => (
              <Grid item xs={12} sm={6} md={3} key={book.id}>
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
                    <Typography variant="body2" color="text.secondary">
                      {book.year}
                    </Typography>
                    <Button onClick={() => handleExpandClick(book.id)}>
                      {expandedId === book.id ? 'Less' : 'More'}
                    </Button>
                    <Collapse in={expandedId === book.id} timeout="auto" unmountOnExit>
                      <CardContent>
                        <Typography paragraph>ISBN: {book.isbn}</Typography>
                        <Typography paragraph>Publisher: {book.publisher}</Typography>
                        <Typography paragraph>Genre: {book.genre}</Typography>
                        <Typography paragraph>Summary: {book.summary}</Typography>
                      </CardContent>
                    </Collapse>
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
