export const validateCallId = async (callId) => {
  try {
    const response = await fetch("/auth", {
      method: "POST",
      headers: {
        Authorization: callId,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(
        `[@ 유효성 검사 실패]: ${response.status} ${response.statusText}`
      );
    } else {
      const { contents } = await response.json();
      const decodedCallId = contents?.output?.callId || contents?.input?.callId;

      return decodedCallId;
    }
  } catch (error) {
    console.error("Error in validateCallId:", error);
    throw error;
  }
};
