import React from "react";
import { Button } from "@mui/material";

function MuiButton({ children }: React.PropsWithChildren) {
  return (
    <Button
      sx={{
        ":hover": {
          background: "#0b4e51",
        },

        background: "#4e9b9b",
      }}
      variant="contained"
      type="submit"
      fullWidth
    >
      {children}
    </Button>
  );
}

export default MuiButton;
