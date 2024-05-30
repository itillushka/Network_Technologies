import { Formik } from 'formik';
import * as yup from 'yup';
import { TextField, Button, Select, MenuItem, Box } from '@mui/material';

const UserRegistrationForm = () => {
  const initialValues = { username: '', password: '', role: '', email: '' };

  const validationSchema = yup.object().shape({
    username: yup.string().required('Username is required'),
    password: yup.string().required('Password is required').min(8, 'Password must be at least 8 characters'),
    role: yup.string().required('Role is required').oneOf(['READER', 'ADMIN'], 'Role must be READER or ADMIN'),
    email: yup.string().required('Email is required').email('Email is not valid'),
  });

  const handleSubmit = (values: any) => {
    console.log(values);
    // Handle form submission here
  };

  return (
    <Formik initialValues={initialValues} onSubmit={handleSubmit} validationSchema={validationSchema}>
      {formik => (
        <form onSubmit={formik.handleSubmit}>
          <TextField label="Username" {...formik.getFieldProps('username')} />
          <TextField label="Password" type="password" {...formik.getFieldProps('password')} />
          <Box width="600px">
          <Select label="Role" {...formik.getFieldProps('role')}>
            <MenuItem value="READER">READER</MenuItem>
            <MenuItem value="ADMIN">ADMIN</MenuItem>
          </Select>
          </Box>
          <TextField label="Email" {...formik.getFieldProps('email')} />
          <Button type="submit"
                  color="primary"
                  variant="contained"
                  className="centered-button">Register</Button>
        </form>
      )}
    </Formik>
  );
};

export default UserRegistrationForm;