export class CreateBookRequestDto {
	isbn: string | undefined;
	title: string | undefined;
	author: string | undefined;
	publisher: string | undefined;
	yearPublished: number | undefined;
	availableCopies: number | undefined;
}