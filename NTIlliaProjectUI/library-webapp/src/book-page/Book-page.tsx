import React, { useState, useEffect } from 'react';
import kobzar from './kobzar.jpg';
import witcher from './witcher.jpg';
import witcher1 from './witcher1.jpg';
import './Book-page.css';
import { AppBar, Toolbar, Box, Card, CardMedia, CardContent, Typography, Collapse, TextField, Container, IconButton, Button } from '@mui/material';
import { ArrowBack, ArrowForward } from '@mui/icons-material';

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
  const [currentIndex, setCurrentIndex] = useState(0);
  const [transitioning, setTransitioning] = useState(false);

  const handleExpandClick = (id: number) => {
    setExpandedId(expandedId === id ? null : id);
  };

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
  };

  const nextBook = () => {
    if (transitioning) return;
    setTransitioning(true);
    setTimeout(() => {
      setCurrentIndex((prevIndex) => (prevIndex + 1) % books.length);
      setTransitioning(false);
    }, 500);
  };

  const prevBook = () => {
    if (transitioning) return;
    setTransitioning(true);
    setTimeout(() => {
      setCurrentIndex((prevIndex) => (prevIndex - 1 + books.length) % books.length);
      setTransitioning(false);
    }, 500);
  };

  const filteredBooks = books.filter((book) =>
    book.title.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const displayBooks = [
    books[(currentIndex - 1 + books.length) % books.length],
    books[currentIndex],
    books[(currentIndex + 1) % books.length],
  ];

  return (
    <>
      <AppBar position="static" className="AppBar">
        <Toolbar sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <Typography variant="h6" component="div">
            My Book Store
          </Typography>
          <Box sx={{ backgroundColor: '#DEB887', borderRadius: '5px' }}>
            <TextField
              label="Search"
              variant="outlined"
              value={searchTerm}
              onChange={handleSearchChange}
              sx={{ marginLeft: 'auto' }}
            />
          </Box>
          <Box sx={{ width: '10%' }} />
        </Toolbar>
      </AppBar>
      <Container className="container">
        <Box mt={2} className="wheel-container">
          <IconButton onClick={prevBook}>
            <ArrowBack />
          </IconButton>
          <Box className={`wheel ${transitioning ? 'transitioning' : ''}`}>
            {displayBooks.map((book, index) => (
              <Card className={`Card ${index === 1 ? 'center' : 'side'}`} key={book.id}>
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
            ))}
          </Box>
          <IconButton onClick={nextBook}>
            <ArrowForward />
          </IconButton>
        </Box>
      </Container>
      <Box className="footer">
        <Typography variant="body1">My Book Store © 2024</Typography>
      </Box>
    </>
  );
};

export default BookPage;
