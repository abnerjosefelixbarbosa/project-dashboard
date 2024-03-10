import { zodResolver } from "@hookform/resolvers/zod";
import { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { updateProjectById } from "../../../service/serviceProject";
import "react-toastify/ReactToastify.css";
import { ToastContainer, toast } from "react-toastify";
import { ValidationFormError } from "../../../exception/validationFormError";
import { useSearchParams } from "react-router-dom";

const date = new Date();
const schema = z.object({
  name: z.string()
    .max(100, "Name max 100")
    .nonempty("Name empty"),
  description: z.string()
    .max(200, "Description max 200")
    .nonempty("Description empty"),
  dateStart: z.coerce.date(),
  dateEnd: z.coerce.date(),
  budget: z.coerce.number(),
});

type FormEdit = z.infer<typeof schema>;

type ModalData = {
  id: string;
  name: string;
  description: string;
  dateStart: Date;
  dateEnd: Date;
  budget: number;
  accountId: string;
}

export function ModalEdit({
  id,
  name,
  description,
  dateStart,
  dateEnd,
  budget
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
  const [params] = useSearchParams();
  const token = params.get('token')!;

  async function handleEdit(data: FormEdit) {
    try {
      data.dateStart = transformeDate(data.dateStart);
      data.dateEnd = transformeDate(data.dateEnd);
      validateForm(data);
      const obj = {
        ...data,
      }
      const res = await updateProjectById(id, token, obj);
      if (res.id !== undefined) {
        setShow(false);
        window.location.reload();
      }
    } catch (e: any) {
      const message = `${e.message}`;
      if (message.includes("Date start future")) {
        setError("dateStart", { message: message });
      } else if (message.includes("Date end equal date start")) {
        setError("dateEnd", { message: message });
      } else if (message.includes("Date end before date start")) {
        setError("dateEnd", { message: message });
      } else if (message.includes("Budget equal 0.00")) {
        setError("budget", { message: message });
      } else {
        toast.error(message);
      }
    }
  }

  function transformeDate(data: Date) {
    data.setDate(data.getDate() + 1);
    data.setHours(date.getHours());
    data.setMinutes(date.getMinutes());
    data.setSeconds(date.getSeconds());
    return data;
  }

  function validateForm(data: FormEdit) {
    if (data.dateStart < date || data.dateStart.toDateString() == date.toDateString()) {
      throw new ValidationFormError("Date start future");
    }
    if (data.dateEnd.toDateString() == data.dateStart.toDateString()) {
      throw new ValidationFormError("Date end equal date start");
    }
    if (data.dateEnd < data.dateStart) {
      throw new ValidationFormError("Date end before date start");
    }
    if (data.budget == 0) {
      throw new ValidationFormError("Budget equal 0.00");
    }
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
                {...register("dateStart")}
                type="date"
                defaultValue={dateStart.toString()}
              />
              <Form.Text className="text_color">
                {errors.dateStart?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>End:</Form.Label>
              <Form.Control
                {...register("dateEnd")}
                type="date"
                defaultValue={dateEnd.toString()}
              />
              <Form.Text className="text_color">
                {errors.dateEnd?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Budget:</Form.Label>
              <Form.Control
                {...register("budget")}
                type="text"
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
