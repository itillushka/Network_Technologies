import { Formik, FormikHelpers } from 'formik';
import * as yup from 'yup';
import { TextField, Button } from '@mui/material';
import { useTranslation } from 'react-i18next';

interface DeleteBookFormProps {
	onSubmit: (values: { bookId: number;},
						 formikHelpers: FormikHelpers<{ bookId: number; }>) => void | Promise<void>;
}
const DeleteBookForm: React.FC<DeleteBookFormProps> = ({ onSubmit }) => {
	const {t, i18n} = useTranslation();
	const initialValues = { bookId: 0};

	const validationSchema = yup.object().shape({
		bookId: yup.string().required('BookId is required'),
	});

	return (
		<Formik initialValues={initialValues} onSubmit={onSubmit} validationSchema={validationSchema}>
			{formik => (
				<form onSubmit={formik.handleSubmit}>
					<TextField label={t('Book ID')} {...formik.getFieldProps('bookId')} />
					<Button type="submit"
									color="primary"
									variant="contained"
									className="centered-button">{t('Delete Book')}</Button>
				</form>
			)}
		</Formik>
	);
};

export default DeleteBookForm;