import {useState} from "react";

export const useFetching = (callback) => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState();

    const fetching = async (...args) => {
        try {
            setIsLoading(true);
            await callback(...args);
        } catch (e) {
            setError(e);
        } finally {
            setIsLoading(false);
        }
    }

    return [fetching, error, isLoading];
}