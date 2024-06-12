import { Formik, FormikHelpers } from 'formik';
import * as yup from 'yup';
import { TextField, Button} from '@mui/material';
import { useTranslation } from 'react-i18next';

interface AddBookDetailsFormProps {
  onSubmit: (values: { bookId: number; genre: string; coverImageURL: string; summary: string; }, formikHelpers: FormikHelpers<{ bookId: number; genre: string; coverImageURL: string; summary: string; }>) => void | Promise<void>;
}
const AddBookDetailsForm: React.FC<AddBookDetailsFormProps> = ({ onSubmit }) => {
  const {t, i18n} = useTranslation();
  const initialValues = { bookId: 0, genre: '', coverImageURL: '', summary: '' };

  const validationSchema = yup.object().shape({
    bookId: yup.number().required('Book ID is required'),
    genre: yup.string().required('Genre is required'),
    coverImageURL: yup.string().required('Cover Image is required'),
    summary: yup.string().required('Summary is required'),
  });


  return (
    <Formik initialValues={initialValues} onSubmit={onSubmit} validationSchema={validationSchema}>
      {formik => (
        <form onSubmit={formik.handleSubmit}>
          <TextField label={t('Book ID')} {...formik.getFieldProps('bookId')} />
          <TextField label={t('Genre')} {...formik.getFieldProps('genre')} />
          <TextField label={t('Cover Image')} {...formik.getFieldProps('coverImageURL')} />
          <TextField label={t('Summary')} {...formik.getFieldProps('summary')} />
          <Button type="submit"
                  color="primary"
                  variant="contained"
                  className="centered-button">{t('Add Book Details')}</Button>
        </form>
      )}
    </Formik>
  );
};

export default AddBookDetailsForm;