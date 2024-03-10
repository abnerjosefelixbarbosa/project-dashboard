import { Project } from "../entities/project";
import { BadRequestError } from "../exception/badRequestError";
import { NotAuthorizedError } from "../exception/notAuthorizedError";
import { BASE_URL } from "../utils/request";

type ObjCreateProject = {
  name: string;
  description: string;
  dateStart: Date;
  dateEnd: Date;
  budget: number;
  accountId: string;
};

export async function createProject(data: ObjCreateProject, token: string) {
  const res = await fetch(`${BASE_URL}/api/projects/create`, {
    method: "POST",
    headers: {
      "content-type": "application/json",
      "authorization": `Bearer ${token}`
    },
    body: JSON.stringify(data),
  });
  if (res.status == 401) {
    throw new NotAuthorizedError("User not authorized");
  }
  const json = await res.json();
  if (json.message) {
    throw new BadRequestError(json.message);
  }
  const obj: Project = { ...json }
  return obj;
}

export async function listProjectsAllByAccountId(accountId: string, token: string) {
  const res = await fetch(`${BASE_URL}/api/projects/list-all-by-account-id?accountId=${accountId}`, {
    method: "GET",
    headers: {
      "authorization": `Bearer ${token}`
    }
  });
  if (res.status == 401) {
    throw new NotAuthorizedError("User not authorized");
  }
  const json = await res.json();
  if (json.message) {
    throw new BadRequestError(json.message);
  }
  const objs: Array<Project> = json.content;
  return objs;
}

export async function deleteProjectById(id: string, token: string) {
  const res = await fetch(`${BASE_URL}/api/projects/delete-by-id?id=${id}`, {
    method: "DELETE",
    headers: {
      "authorization": `Bearer ${token}`
    }
  });
  if (res.status == 401) {
    throw new NotAuthorizedError("User not authorized");
  }
}

type ObjUpdateProjectById = {
  name: string,
  description: string,
  dateStart: Date,
  dateEnd: Date,
  budget: number
}

export async function updateProjectById(id: string, token: string, data: ObjUpdateProjectById) {
  const res = await fetch(`${BASE_URL}/api/projects/update-by-id?id=${id}`, {
    method: "PATCH",
    headers: {
      "authorization": `Bearer ${token}`,
      "content-type": "application/json"
    },
    body: JSON.stringify(data)
  });
  if (res.status === 401) {
    throw new NotAuthorizedError("User not authorized");
  }
  const json = await res.json();
  const obj: Project = { ...json }
  return obj;
}