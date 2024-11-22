export const getData = async () => {
  const bodyData = {
    msgKey: "home",
    contents: {
      output: {
        callId: "573a394c-12b7-123e-7da3-5254008fb306",
        params: {},
      },
    },
  };

  try {
    const response = await fetch("/api", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(bodyData),
    });

    if (!response.ok) {
      throw new Error(
        `[@ 데이터 가져오기 실패]: ${response.status} ${response.statusText}`
      );
    } else {
      return await response.json();
    }
  } catch (error) {
    console.log("Error in getData: ", error);
    throw error;
  }
};
