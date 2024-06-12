import { Formik, FormikHelpers } from 'formik';
import * as yup from 'yup';
import { TextField, Button, Select, MenuItem, Box } from '@mui/material';
import { useTranslation } from 'react-i18next';

interface UserRegistrationFormProps {
  onSubmit: (values: { username: string; password: string; role: string; email: string; }, formikHelpers: FormikHelpers<{ username: string; password: string; role: string; email: string; }>) => void | Promise<void>;
}

const UserRegistrationForm: React.FC<UserRegistrationFormProps> = ({ onSubmit }) => {
  const {t, i18n} = useTranslation();
  const initialValues = { username: '', password: '', role: '', email: '' };

  const validationSchema = yup.object().shape({
    username: yup.string().required('Username is required'),
    password: yup.string().required('Password is required').min(8, 'Password must be at least 8 characters'),
    role: yup.string().required('Role is required').oneOf(['ROLE_READER', 'ROLE_ADMIN'], 'Role must be READER or ADMIN'),
    email: yup.string().required('Email is required').email('Email is not valid'),
  });

  return (
    <Formik initialValues={initialValues} onSubmit={onSubmit} validationSchema={validationSchema}>
      {formik => (
        <form onSubmit={formik.handleSubmit}>
          <TextField label={t('Username')} {...formik.getFieldProps('username')} />
          <TextField label={t('Password')} type="password" {...formik.getFieldProps('password')} />
          <Box width="600px">
            <Select label="Role" {...formik.getFieldProps('role')}>
              <MenuItem value="ROLE_READER">ROLE_READER</MenuItem>
              <MenuItem value="ROLE_ADMIN">ROLE_ADMIN</MenuItem>
            </Select>
          </Box>
          <TextField label={t('Email')} {...formik.getFieldProps('email')} />
          <Button type="submit"
                  color="primary"
                  variant="contained"
                  className="centered-button">{t('REGISTER')}</Button>
        </form>
      )}
    </Formik>
  );
};

export default UserRegistrationForm;