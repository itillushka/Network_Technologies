export class BookResponseDto {
  bookID: number | undefined;
  isbn: string | undefined;
  title: string | undefined;
  author: string | undefined;
  publisher: string | undefined;
  yearPublished: number | undefined;
  isAvailable: boolean | undefined;
}