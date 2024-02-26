import { User } from "./User";
import { Level } from "./level";

export type Account = {
    id: String;
	dateCreation: Date;
	level: Level;
	user: User;
}