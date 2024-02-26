import { Account } from "./account";

export type Project = {
    id: string;
	name: string;
	description: string;
	dateStart: Date;
	dateEnd: Date;
	budget: number;
	account: Account;
}