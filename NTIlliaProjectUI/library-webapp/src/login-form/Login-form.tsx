import React, { useMemo, useCallback } from 'react';
import { Formik } from 'formik';
import * as yup from 'yup';
import { TextField, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import './Login-form.css';
function LoginForm() {
	const navigate = useNavigate();

	const initialValues = useMemo(() => ({ username: '', password: '' }), []);

	const handleSubmit = useCallback(
		(values: any, formik: { resetForm: () => void }) => {
			console.log(values);
			formik.resetForm();
			navigate('/home');
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
						label="Username"
						value={formik.values.username}
						onChange={formik.handleChange}
						error={formik.touched.username && Boolean(formik.errors.username)}
						helperText={formik.touched.username && formik.errors.username}
					/>
					<TextField
						id="password"
						name="password"
						label="Password"
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
						Login
					</Button>
				</form>
			)}
		</Formik>
	);
}

export default LoginForm;
