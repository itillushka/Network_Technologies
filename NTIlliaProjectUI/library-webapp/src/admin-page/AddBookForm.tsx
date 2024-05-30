import { Formik } from 'formik';
import * as yup from 'yup';
import { TextField, Button } from '@mui/material';

const AddBookForm = () => {
  const initialValues = { ISBN: '', title: '', author: '', publisher: '', yearPublished: '', availableCopies: '' };

  const validationSchema = yup.object().shape({
    ISBN: yup.string().required('ISBN is required'),
    title: yup.string().required('Title is required'),
    author: yup.string().required('Author is required'),
    publisher: yup.string().required('Publisher is required'),
    yearPublished: yup.number().required('Year Published is required').min(1900, 'Year Published must be after 1900'),
    availableCopies: yup.number().required('Available Copies is required').min(0, 'Available Copies must be at least 0'),
  });

  const handleSubmit = (values: any) => {
    console.log(values);
    // Handle form submission here
  };

  return (
    <Formik initialValues={initialValues} onSubmit={handleSubmit} validationSchema={validationSchema}>
      {formik => (
        <form onSubmit={formik.handleSubmit}>
          <TextField label="ISBN" {...formik.getFieldProps('ISBN')} />
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