import React, { useMemo, useCallback } from 'react';
import { Formik } from 'formik';
import * as yup from 'yup';
import { TextField, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import './Login-form.css';
import axios from 'axios';
import { useApi } from '../api/ApiProvider';
import { useTranslation } from 'react-i18next';
function LoginForm() {
	const navigate = useNavigate();
	const apiClient = useApi();
	const {t, i18n} = useTranslation();

	const initialValues = useMemo(() => ({ username: '', password: '' }), []);

	const handleSubmit = useCallback(
		(values: any, formik: { resetForm: () => void }) => {
			console.log(values);
			formik.resetForm();

			apiClient.login(values).then((response) => {
				console.log(response);
				console.log(localStorage.getItem('token'));
				if (!response.success){
					console.log("Login failed");
					return;
				}
			});

			/*const client = axios.create({
				baseURL: 'http://localhost:8080/api',
			});
			client.post('/auth/login', values).then((response) => {
          console.log(response.data);
			});*/
			navigate('/');
		},
		[navigate],
	);

	const validationSchema = useMemo(
		() =>
			yup.object().shape({
				username: yup.string().required('Username is required'),
				password: yup
					.string()
					.required('Password is required')
					.min(8, 'Password must be at least 8 characters'),
			}),
		[],
	);

	return (
		<Formik
			initialValues={initialValues}
			onSubmit={handleSubmit}
			validationSchema={validationSchema}
			validateOnChange
			validateOnBlur
		>
			{(formik) => (
				<form
					id="loginForm"
					className="Login-form centered-form"
					onSubmit={formik.handleSubmit}
					noValidate
				>
					<TextField
						id="username"
						name="username"
						label={t('Username')}
						value={formik.values.username}
						onChange={formik.handleChange}
						error={formik.touched.username && Boolean(formik.errors.username)}
						helperText={formik.touched.username && formik.errors.username}
					/>
					<TextField
						id="password"
						name="password"
						label={t('Password')}
						type="password"
						value={formik.values.password}
						onChange={formik.handleChange}
						error={formik.touched.password && Boolean(formik.errors.password)}
						helperText={formik.touched.password && formik.errors.password}
					/>
					<Button
						color="primary"
						variant="contained"
						className="centered-button"
						type="submit"
					>
						{t('Login')}
					</Button>
				</form>
			)}
		</Formik>
	);
}

export default LoginForm;
