import { Project } from "../types/project";
import { supabase } from "./subabase";

export async function save(data: Project) {
  if (data.end <= data.start) {
    throw new Error("end date invalid");
  }
  const { error } = await supabase.from("project").insert({
    id: crypto.randomUUID(),
    name: data.name,
    description: data.description,
    start: data.start,
    end: data.end,
    budget: data.budget,
    user_id: data.user_id,
  });
  if (error) throw error;
}

export async function getAllByUserId(userId: string) {
  const { data, error } = await supabase
    .from("project")
    .select("*")
    .eq("user_id", userId)
    .limit(5);
  if (error) throw error;
  return data;
}

export async function edit(data: Project) {
  if (data.end <= data.start) {
    throw new Error("end date invalid");
  }
  const { error } = await supabase.from("project").update({
    name: data.name,
    description: data.description,
    start: data.start,
    end: data.end,
    budget: data.budget,
  }).eq("id", data.id);
  if (error) throw error;
}

export async function deleteById(id: string) {
  const { error } = await supabase.from("project").delete().eq("id", id);
  if (error) throw error;
}
