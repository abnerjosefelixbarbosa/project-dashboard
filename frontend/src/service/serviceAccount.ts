import { UnauthorizedError } from "../exception/UnauthorizedError";
import { BASE_URL } from "../utils/request";

export async function loginAccount(data: any) {
  const res = await fetch(`${BASE_URL}/api/accounts/login`, {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if (res.status === 401) {
    throw new UnauthorizedError("Unauthorized")
  }
  const json = await res.json();
  return {
    id: json.id,
    dateCreation: json.dateCreation,
    level: json.level,
    user: json.userResponse,
    token: json.token
  };
}