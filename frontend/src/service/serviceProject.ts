import { BadRequestError } from "../exception/badRequestError";
import { Project } from "../types/project";
import { BASE_URL } from "../utils/request";

export async function createProject(data: any, token: string) {
  const res = await fetch(`${BASE_URL}/api/projects/create`, {
    method: "post",
    headers: {
      "content-type": "application/json",
      "authorization": `Bearer ${token}`
    },
    body: JSON.stringify(data),
  });
  const json = await res.json();
  if (json.message) {
    throw new BadRequestError(json.message);
  }
  const obj: Project = { ...json }
  return obj;
}

export async function listProjectsAllByAccountId(accountId: string, token: string) {
  const res = await fetch(`${BASE_URL}/api/projects/list-all-by-account-id?accountId=${accountId}`, {
    method: "get",
    headers: {
      "content-type": "application/json",
      "authorization": `Bearer ${token}`
    }
  });
  const json = await res.json();
  const objs: Array<Project> = json.content;
  return objs;
}

export async function edit(data: any) {
  
}

export async function deleteById(id: string) {
  
}