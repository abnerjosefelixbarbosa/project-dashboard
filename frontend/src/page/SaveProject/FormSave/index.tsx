import { Button, Container, Form, Row } from "react-bootstrap";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { save } from "../../../service/serviceProject";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/ReactToastify.css";
import { useEffect, useState } from "react";
import { User } from "@supabase/supabase-js";
import { getUser } from "../../../service/auth";

const schema = z.object({
  name: z.string().min(1, "min 1 character").max(100, "max 100 characters"),
  description: z
    .string()
    .min(1, "min 1 character")
    .max(200, "max 200 character"),
  start: z.coerce.date().min(new Date(), "min future date"),
  end: z.coerce.date().min(new Date(), "min future date"),
  budget: z.coerce.number(),
});

type FormSave = z.infer<typeof schema>;

export function FromSave() {
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm<FormSave>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });
  const [user, setUser] = useState<User>();
  
  useEffect(() => {
    checkUser();
  }, []);

  function checkUser() {
    getUser().then((user) => {
      if (user !== null) {
        setUser(user);
      } 
    });
  }

  function handleSave(data: FormSave) {
    save({ ...data, user_id: `${user?.id}`, id: ""})
      .then(() => {
        toast.success("project saved", {
          autoClose: 2000,
          position: "top-center",
        });
      })
      .catch((e) => {
        if (e.message.includes("end date")) {
          setError("end", { message: e.message });
        } else if (e.message) {
          toast.error(e.message);
        }
      });
  }

  return (
    <>
      <ToastContainer />
      <Container className="container_project_save_form">
        <Row>
          <Form onSubmit={handleSubmit(handleSave)}>
            <Form.Group className="mb-3">
              <Form.Label>Name:</Form.Label>
              <Form.Control
                {...register("name")}
                type="text"
                placeholder="Enter name"
              />
              <Form.Text className="text_color">
                {errors.name?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Description:</Form.Label>
              <Form.Control
                {...register("description")}
                as="textarea"
                placeholder="Enter description"
                style={{ height: "100px" }}
              />
              <Form.Text className="text_color">
                {errors.description?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Start:</Form.Label>
              <Form.Control
                {...register("start")}
                type="date"
              />
              <Form.Text className="text_color">
                {errors.start?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>End:</Form.Label>
              <Form.Control
                {...register("end")}
                type="date"
              />
              <Form.Text className="text_color">
                {errors.end?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Budget:</Form.Label>
              <Form.Control
                {...register("budget")}
                type="text"
                placeholder="Enter budget"
                onChange={(e) => {
                  let value = e.target.value;
                  value = value.replace(/(\D)/g, "");
                  value = value.replace(/(\d+)(\d{2})$/, "$1.$2");
                  e.target.value = value;
                }}
              />
              <Form.Text className="text_color">
                {errors.budget?.message}
              </Form.Text>
            </Form.Group>
            <div className="d-grid gap-2">
              <Button variant="primary" type="submit" size="lg">
                Save
              </Button>
            </div>
          </Form>
        </Row>
      </Container>
    </>
  );
}
