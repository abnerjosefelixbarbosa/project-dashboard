import { zodResolver } from "@hookform/resolvers/zod";
import { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { edit as serviceEdit } from "../../../service/serviceProject";
import "react-toastify/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";

const schema = z.object({
  name: z.string().max(100, "max 100 characters").nonempty("name empty"),
  description: z
    .string()
    .max(200, "max 200 character")
    .nonempty("description empty"),
  start: z.coerce.date().min(new Date(), "min future date"),
  end: z.coerce.date().min(new Date(), "min future date"),
  budget: z.coerce.number(),
});

type FormEdit = z.infer<typeof schema>;

interface ModalData {
  id: string;
  name: string;
  description: string;
  start: Date;
  end: Date;
  budget: number;
}

export function ModalEdit({
  id,
  name,
  description,
  start,
  end,
  budget,
}: ModalData) {
  const [show, setShow] = useState<boolean>(false);
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm<FormEdit>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  function handleEdit(data: FormEdit) {
    serviceEdit({ ...data, id: id, user_id: "" })
      .then(() => toast.success("project edited"))
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

      <Button variant="primary" onClick={handleShow}>
        Edit
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Edit</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit(handleEdit)}>
            <Form.Group className="mb-3">
              <Form.Label>Name:</Form.Label>
              <Form.Control
                {...register("name")}
                type="text"
                placeholder="Enter name"
                defaultValue={name}
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
                defaultValue={description}
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
                defaultValue={start.toString()}
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
                defaultValue={end.toString()}
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
                defaultValue={budget}
              />
              <Form.Text className="text_color">
                {errors.budget?.message}
              </Form.Text>
            </Form.Group>
            <div className="d-grid gap-2">
              <Button variant="primary" type="submit" size="lg">
                Edit
              </Button>
            </div>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" size="lg" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
