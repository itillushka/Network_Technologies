import React from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import LoginForm from './login-form/Login-form';
import HomePage from './home-page/Home-page';
import LoanPage from './loan-page/Loan-page';
import BookPage from './book-page/Book-page';
import AdminPage from './admin-page/Admin-page';
import AdminLoanPage from './admin-page/AdminLoan-page';
import ApiProvider from './api/ApiProvider';

function App() {
	return (
		<BrowserRouter>
			<ApiProvider>
					<div className="App">
							<Routes>
									<Route path="/login" element={<LoginForm />} />
									<Route path="/book" element={<BookPage />} />
									<Route path="/loan" element={<LoanPage />} />
									<Route path="/" element={<HomePage />} />
									<Route path="/admin" element={<AdminPage />} />
									<Route path="/adminLoan" element={<AdminLoanPage />} />
							</Routes>
					</div>
			</ApiProvider>
		</BrowserRouter>
	);
}

export default App;
