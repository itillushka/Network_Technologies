import { Formik, FormikHelpers } from 'formik';
import * as yup from 'yup';
import { TextField, Button } from '@mui/material';

interface CreateBookFormProps {
  onSubmit: (values: { isbn: string; title: string; author: string; publisher: string; yearPublished: number; availableCopies: number;},
             formikHelpers: FormikHelpers<{ isbn: string; title: string; author: string; publisher: string; yearPublished: number; availableCopies: number; }>) => void | Promise<void>;
}
const AddBookForm: React.FC<CreateBookFormProps> = ({ onSubmit }) => {
  const initialValues = { isbn: '', title: '', author: '', publisher: '', yearPublished: 0, availableCopies: 0 };

  const validationSchema = yup.object().shape({
    isbn: yup.string().required('ISBN is required'),
    title: yup.string().required('Title is required'),
    author: yup.string().required('Author is required'),
    publisher: yup.string().required('Publisher is required'),
    yearPublished: yup.number().required('Year Published is required').min(1900, 'Year Published must be after 1900'),
    availableCopies: yup.number().required('Available Copies is required').min(0, 'Available Copies must be at least 0'),
  });

  return (
    <Formik initialValues={initialValues} onSubmit={onSubmit} validationSchema={validationSchema}>
      {formik => (
        <form onSubmit={formik.handleSubmit}>
          <TextField label="ISBN" {...formik.getFieldProps('isbn')} />
          <TextField label="Title" {...formik.getFieldProps('title')} />
          <TextField label="Author" {...formik.getFieldProps('author')} />
          <TextField label="Publisher" {...formik.getFieldProps('publisher')} />
          <TextField label="Year Published" {...formik.getFieldProps('yearPublished')} />
          <TextField label="Available Copies" {...formik.getFieldProps('availableCopies')} />
          <Button type="submit"
                  color="primary"
                  variant="contained"
                  className="centered-button">Add Book</Button>
        </form>
      )}
    </Formik>
  );
};

export default AddBookForm;