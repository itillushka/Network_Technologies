export class AddBookDetailsRequestDto {
	bookId: number | undefined;
	genre: string | undefined;
	summary: string | undefined;
	coverImageURL: string | undefined;
}