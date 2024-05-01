import React from 'react';
import { Grid, Paper, Typography } from '@mui/material';

const HomePage = () => {
	// Dummy data for books
	const books = [
		{
			id: 1,
			title: 'Book 1',
			author: 'Author 1',
			coverImage: 'book1.jpg',
		},
		{
			id: 2,
			title: 'Book 2',
			author: 'Author 2',
			coverImage: 'book2.jpg',
		},
		{
			id: 3,
			title: 'Book 3',
			author: 'Author 3',
			coverImage: 'book3.jpg',
		},
		// Add more books as needed
	];

	return (
		<Grid container spacing={2}>
			{books.map((book) => (
				<Grid item xs={4} key={book.id}>
					<Paper elevation={3} sx={{ p: 2 }}>
						<img
							src={book.coverImage}
							alt={book.title}
							style={{ width: '100%' }}
						/>
						<Typography variant="h6" sx={{ mt: 1 }}>
							{book.title}
						</Typography>
						<Typography variant="subtitle1" sx={{ mt: 0.5 }}>
							{book.author}
						</Typography>
					</Paper>
				</Grid>
			))}
		</Grid>
	);
};

export default HomePage;
