import { Formik } from 'formik';
import * as yup from 'yup';
import { TextField, Button} from '@mui/material';

const AddBookDetailsForm = () => {
  const initialValues = { bookId: '', genre: '', coverImage: '', summary: '' };

  const validationSchema = yup.object().shape({
    bookId: yup.string().required('Book ID is required'),
    genre: yup.string().required('Genre is required'),
    coverImage: yup.string().required('Cover Image is required'),
    summary: yup.string().required('Summary is required'),
  });

  const handleSubmit = (values: any) => {
    console.log(values);
    // Handle form submission here
  };

  return (
    <Formik initialValues={initialValues} onSubmit={handleSubmit} validationSchema={validationSchema}>
      {formik => (
        <form onSubmit={formik.handleSubmit}>
          <TextField label="Book ID" {...formik.getFieldProps('bookId')} />
          <TextField label="Genre" {...formik.getFieldProps('genre')} />
          <TextField label="Cover Image" {...formik.getFieldProps('coverImage')} />
          <TextField label="Summary" {...formik.getFieldProps('summary')} />
          <Button type="submit"
                  color="primary"
                  variant="contained"
                  className="centered-button">Add Book Details</Button>
        </form>
      )}
    </Formik>
  );
};

export default AddBookDetailsForm;