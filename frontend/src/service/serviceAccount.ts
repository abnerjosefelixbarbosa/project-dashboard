
import { Account } from "../entities/account";
import { Level } from "../entities/level";
import { User } from "../entities/user";
import { BadRequestError } from "../exception/badRequestError";
import { BASE_URL } from "../utils/request";

type ObjLoginAccountResponse = {
  id: string,
	dateCreation: Date,
	level: Level,
	user: User,
  token: string
}

export type ObjLoginAccount = {
  emailUser: string,
  passwordUser: string
}

export type ObjCreateAccount = {
  nameUser: string,
  emailUser: string,
  passwordUser: string,
  dateBirthUser: Date
}

export async function loginAccount(data: ObjLoginAccount) {
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
  const obj: ObjLoginAccountResponse = { ...json }
  return obj;
}

export async function createAccount(data: ObjCreateAccount) {
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