import { BASE_URL } from "../utils/request";

export async function loginAccount(data: any) {
  const res = await fetch(`${BASE_URL}/api/accounts/login`, {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data),
  });
  const json = await res.json();
  return json;
}