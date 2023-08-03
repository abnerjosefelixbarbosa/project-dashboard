import { Project } from "../types/project";
import { supabase } from "./subabase";

export async function save(data: Project) {
  const { error } = await supabase.from("Project").insert({
    name: data.name,
    description: data.description,
    start: data.start,
    end: data.end,
    budget: data.budget,
    user_id: data.user_id,
  });
  if (error?.message) {
    throw new Error(error.message);
  }
}

export async function getAllByUserId(userId: string) {
  const { data, error } = await supabase
    .from("Project")
    .select("*")
    .eq("user_id", userId);
  if (error?.message) {
    throw new Error(error.message);
  }
  return data;
}