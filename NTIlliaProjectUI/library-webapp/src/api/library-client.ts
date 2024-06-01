import axios, { AxiosError, AxiosInstance, AxiosResponse } from 'axios';
import { LoginRequestDto } from './dto/login-request.dto';
import { LoginResponseDto } from './dto/login-response.dto';
import { BookResponseDto } from './dto/book-response.dto';
import { RegisterUserRequestDto } from './dto/register-user-request.dto';
import { LoanResponseDto } from './dto/loan-response.dto';
import { CreateBookRequestDto } from './dto/create-book-request.dto';
import AddBookDetailsForm from '../admin-page/AddBookDetailsForm';
import { AddBookDetailsRequestDto } from './dto/add-book-details-request.dto';

type ClientResponse = {
	success: boolean;
	data: any;
	status: number;
}

export class LibraryClient {
	private baseUrl = 'http://localhost:8080/api';
	private client: AxiosInstance;

	constructor() {
		this.client = axios.create({
			baseURL: this.baseUrl
		});
		}

		public async login(data: LoginRequestDto): Promise<ClientResponse> {
			try {
				const response: AxiosResponse<LoginResponseDto> = await this.client.post('/auth/login', data);

				this.client.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
				const token = response.data.token;
				if (typeof token === 'string') {
					localStorage.removeItem('token');
					localStorage.setItem('token', token);
				}
				return{
					success: true,
					data: response.data.token,
					status: response.status
				};
				} catch (error) {
				const axiosError = error as AxiosError<Error>;
					return {
						success: false,
						data: axiosError.response?.data,
						status: axiosError.response?.status || 0
					};
			}
		}

	public async getAllBooks(): Promise<ClientResponse> {
		try {

			const response: AxiosResponse<BookResponseDto[]> = await this.client.get('/books/all');

			return {
				success: true,
				data: response.data,
				status: response.status
			};
		} catch (error) {
			const axiosError = error as AxiosError<Error>;

			return {
				success: false,
				data: axiosError.response?.data,
				status: axiosError.response?.status || 0
			};
		}
	}

	public async createBook(data: CreateBookRequestDto): Promise<number> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}
			console.log(axiosConfig.headers.Authorization);
			const response: AxiosResponse<LoginResponseDto> = await this.client.post('/books/create', data, axiosConfig);

			this.client.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
			return response.status;
		} catch (error) {
			const axiosError = error as AxiosError<Error>;
			return axiosError.response?.status || 0;
		}
	}

	public async addBookDetails(data: AddBookDetailsRequestDto): Promise<number> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}
			console.log(axiosConfig.headers.Authorization);
			const response: AxiosResponse<LoginResponseDto> = await this.client.post('/bookdetails/addDetails', data, axiosConfig);

			this.client.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
			return response.status;
		} catch (error) {
			const axiosError = error as AxiosError<Error>;
			return axiosError.response?.status || 0;
		}
	}


	public async registerUser(data: RegisterUserRequestDto): Promise<number> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}
			const response: AxiosResponse<LoginResponseDto> = await this.client.post('/auth/register', data, axiosConfig);

			this.client.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
			return response.status;
		} catch (error) {
			const axiosError = error as AxiosError<Error>;
			return axiosError.response?.status || 0;
		}
	}

	public async getAllLoans(): Promise<ClientResponse> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}

			const response: AxiosResponse<LoanResponseDto[]> = await this.client.get('/loans/all', axiosConfig);

			return {
				success: true,
				data: response.data,
				status: response.status
			};
		} catch (error) {
			const axiosError = error as AxiosError<Error>;

			return {
				success: false,
				data: axiosError.response?.data,
				status: axiosError.response?.status || 0
			};
		}
	}

}

