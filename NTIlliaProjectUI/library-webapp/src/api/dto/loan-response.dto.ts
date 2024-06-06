export class LoanResponseDto {
	loanID:  number | undefined;
	bookID: number | undefined;
	loanDate: string | undefined;
	dueDate: string | undefined;
	returnDate: string | undefined;
	status:  string | undefined;
	coverImageURL: string | undefined;
}