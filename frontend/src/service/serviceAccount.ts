
import { Account } from "../entities/account";
import { Level } from "../entities/level";
import { User } from "../entities/user";
import { BadRequestError } from "../exception/badRequestError";
import { BASE_URL } from "../utils/request";

type LoginResponse = {
  id: string,
	dateCreation: Date,
	level: Level,
	user: User,
  token: string
}

export async function loginAccount(data: any) {
  const res = await fetch(`${BASE_URL}/api/accounts/login`, {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data),
  });
  const json = await res.json();
  if (json.message) {
    throw new BadRequestError(json.message);
  }
  const obj: LoginResponse = { ...json }
  return obj;
}

export async function createAccount(data: any) {
  const res = await fetch(`${BASE_URL}/api/accounts/create`, {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data),
  });
  const json = await res.json();
  if (json.message) {
    throw new BadRequestError(json.message);
  }
  const obj: Account = { ...json }
  return obj;
}