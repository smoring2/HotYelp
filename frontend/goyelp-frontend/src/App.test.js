import { render, screen } from '@testing-library/react';
import App from './App';

test('renders hottest yelp biz tagline', () => {
  render(<App />);
  const linkElement = screen.getByText(/Hottest Yelp Biz Near You!/i);
  expect(linkElement).toBeInTheDocument();
});
