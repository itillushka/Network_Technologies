import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginForm from './login-form/Login-form';
import HomePage from './home-page/Home-page';
import LoanPage from './loan-page/Loan-page';
import BookPage from './book-page/Book-page';

function App() {
	return (
			<Router>
					<div className="App">
							<Routes>
									<Route path="/login" element={<LoginForm />} />
									<Route path="/book" element={<BookPage />} />
									<Route path="/loan" element={<LoanPage />} />
									<Route path="/" element={<HomePage />} />
							</Routes>
					</div>
			</Router>
	);
}

export default App;
