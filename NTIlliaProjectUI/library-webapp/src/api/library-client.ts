import axios, { AxiosError, AxiosInstance, AxiosResponse } from 'axios';
import { LoginRequestDto } from './dto/login-request.dto';
import { LoginResponseDto } from './dto/login-response.dto';
import { BookResponseDto } from './dto/book-response.dto';
import { RegisterUserRequestDto } from './dto/register-user-request.dto';
import { LoanResponseDto } from './dto/loan-response.dto';
import { CreateBookRequestDto } from './dto/create-book-request.dto';
import { AddBookDetailsRequestDto } from './dto/add-book-details-request.dto';
import { DeleteBookRequestDto } from './dto/delete-book-details-request.dto';
import { BookDetailsResponseDto } from './dto/book-details-response.dto';
import { CreateLoanRequestDto } from './dto/create-loan-request.dto';
import { LoanUpdateStatusDto } from './dto/loan-update-status.dto';
import { ReturnLoanRequestDto } from './dto/return-loan-request.dto';
import { ReviewCreateRequestDto } from './dto/review-create-request.dto';
import { ReviewResponseDto } from './dto/review-response.dto';

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

	public async deleteBook(data: DeleteBookRequestDto): Promise<number> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}
			console.log(axiosConfig.headers.Authorization);
			const response: AxiosResponse<LoginResponseDto> = await this.client.delete(`/books/${data.bookId}`, axiosConfig);

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

	public async getAllLoansAdmin(): Promise<ClientResponse> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}

			const response: AxiosResponse<LoanResponseDto[]> = await this.client.get('/loans/allAdmin', axiosConfig);

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

	public async getBookDetail(bookId: number): Promise<ClientResponse> {
		try {
			const response: AxiosResponse<BookDetailsResponseDto> = await this.client.get(`/bookdetails/${bookId}`);
			return {
				success: true,
				data: response.data,
				status: response.status,
			};
		} catch (error) {
			const axiosError = error as AxiosError<Error>;
			if (axiosError.response?.status === 500) {
				const defaultDetails: BookDetailsResponseDto = {
					id: -1,
					bookid: bookId,
					genre: "No data",
					summary: "No data",
					coverImageURL:
						"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNT0xwyLstvC7wH8jYIKur3GTcSq-g6fj2EbL4wk-qaONHYjBswa3rpFsZJeEjuXcG-lw&usqp=CAU",
				};
				return {
					success: true,
					data: defaultDetails,
					status: 200,
				};
			}
			return {
				success: false,
				data: axiosError.response?.data,
				status: axiosError.response?.status || 500,
			};
		}
	}

	public async getReviews(bookId: number): Promise<ClientResponse> {
		try {
			const response: AxiosResponse<ReviewResponseDto> = await this.client.get(`/reviews/${bookId}`);
			return {
				success: true,
				data: response.data,
				status: response.status,
			};
		} catch (error) {
			const axiosError = error as AxiosError<Error>;
			return {
				success: false,
				data: axiosError.response?.data,
				status: axiosError.response?.status || 500,
			};
		}
	}

	public async createLoan(data: CreateLoanRequestDto): Promise<number> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}
			console.log(axiosConfig.headers.Authorization);
			const response: AxiosResponse<LoginResponseDto> = await this.client.post('/loans/newLoan', data, axiosConfig);

			this.client.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
			return response.status;
		} catch (error) {
			const axiosError = error as AxiosError<Error>;
			return axiosError.response?.status || 0;
		}
	}

	public async returnLoan(data: ReturnLoanRequestDto): Promise<number> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}
			console.log(axiosConfig.headers.Authorization);
			const response: AxiosResponse<LoginResponseDto> = await this.client.post('/loans/return', data, axiosConfig);

			this.client.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
			return response.status;
		} catch (error) {
			const axiosError = error as AxiosError<Error>;
			return axiosError.response?.status || 0;
		}
	}

	public async updateLoan(data: LoanUpdateStatusDto, loanID: number): Promise<number> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}
			console.log(axiosConfig.headers.Authorization);
			const response: AxiosResponse<LoginResponseDto> = await this.client.post(`/loans/${loanID}/status`, data, axiosConfig);

			this.client.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
			return response.status;
		} catch (error) {
			const axiosError = error as AxiosError<Error>;
			return axiosError.response?.status || 0;
		}
	}

	public async createReview(data: ReviewCreateRequestDto, bookId: number): Promise<number> {
		try {
			const token = localStorage.getItem('token');
			const axiosConfig = {
				headers: {
					'Authorization': 'Bearer ' + token
				}
			}
			console.log(axiosConfig.headers.Authorization);
			const response: AxiosResponse<LoginResponseDto> = await this.client.post(`/reviews/${bookId}/leftReview`, data, axiosConfig);

			this.client.defaults.headers.common['Authorization'] = `Bearer ${response.data.token}`;
			return response.status;
		} catch (error) {
			const axiosError = error as AxiosError<Error>;
			return axiosError.response?.status || 0;
		}
	}

}

