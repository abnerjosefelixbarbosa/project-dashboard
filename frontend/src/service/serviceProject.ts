import { Project } from "../entities/project";
import { BadRequestError } from "../exception/badRequestError";
import { BASE_URL } from "../utils/request";

export type ObjCreateProject = {
  name: string;
  description: string;
  dateStart: Date;
  dateEnd: Date;
  budget: number;
  accountId: string;
};

export async function createProject(data: ObjCreateProject, token: string) {
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
      "authorization": `Bearer ${token}`
    }
  });
  const json = await res.json();
  if (json.message) {
    throw new BadRequestError(json.message);
  }
  const objs: Array<Project> = json.content;
  return objs;
}

export async function deleteProjectById(id: string, token: string) {
  await fetch(`${BASE_URL}/api/projects/delete-by-id?id=${id}`, {
    method: "delete",
    headers: {
      "authorization": `Bearer ${token}`
    }
  });
}