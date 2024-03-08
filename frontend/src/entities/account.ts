import { User } from "./user";
import { Level } from "./level";

export type Account = {
    id: string;
	dateCreation: Date;
	level: Level;
	user: User;
}